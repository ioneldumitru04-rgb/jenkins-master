
// Funcție pentru a lua branch-urile remote
def getRemoteBranches(String gitUrl) {
    def branches = []
    
    try {
        // Execută git ls-remote pentru a lua toate branch-urile
        def command = "git ls-remote --heads ${gitUrl}"
        def process = command.execute()
        process.waitFor()
        
        def output = process.text
        output.eachLine { line ->
            // Format: hash refs/heads/branch-name
            if (line.contains('refs/heads/')) {
                def branchName = line.split('refs/heads/')[1].trim()
                branches.add(branchName)
            }
        }
    } catch (Exception e) {
        println "Error getting branches: ${e.message}"
    }
    
    return branches
}
//Oricare dintre branchuri deoarece tot flow este liniar per branch
gitUrl = "https://github.com/ioneldumitru04-rgb/Snapshots"

def branches = getRemoteBranches(gitUrl)

println "Found branches: ${branches}"

branches.each { branchName ->
    def jobName = "VERIFICATION_${branchName.replaceAll('/', '-')}_FRONTEND"
    
    pipelineJob(jobName) {
        description("Verification: ${branchName}")        
        parameters {
            stringParam('REPO', "https://github.com/ioneldumitru04-rgb/CRM-frontend", "SCM Repository")
            stringParam('BRANCH', branchName, 'SCM Branch to clone')
            booleanParam('RUN_TESTS', true, 'Run tests')
        }
        
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url("https://github.com/ioneldumitru04-rgb/CRM-frontend")
                        }
                        branch(branchName)
                    }
                }
                scriptPath('Jenkinsfile')
            }
        }
        triggers {
            githubPush()
        }
    }
}
branches.each { branchName ->
    def jobName = "VERIFICATION_${branchName.replaceAll('/', '-')}_BACKEND"
    
    pipelineJob(jobName) {
        description("Verification: ${branchName}")        
        parameters {
            stringParam('REPO', "https://github.com/ioneldumitru04-rgb/CRM-backend", "SCM Repository")
            stringParam('BRANCH', branchName, 'SCM Branch to clone')
            booleanParam('RUN_TESTS', true, 'Run tests')
        }
        
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url("https://github.com/ioneldumitru04-rgb/CRM-backend")
                        }
                        branch(branchName)
                    }
                }
                scriptPath('Jenkinsfile')
            }
        }
        triggers {
            githubPush()
        }
    }
}
//PRODUCTION JOBS

branches.each { branchName ->
    def jobName = "DELIVERY_${branchName.replaceAll('/', '-')}_CRM"
    
    pipelineJob(jobName) {
        description("DELIVERY: ${branchName}")
        parameters {
            stringParam('BRANCH', branchName, 'SCM Branch to clone')
            booleanParam('RUN_TESTS', true, 'Run tests')
        }
    }
}
//VALIDATOR SNAPSHOT
branches.each { branchName ->
    def jobName = "SNAPSHOT_${branchName.replaceAll('/', '-')}"
    
    pipelineJob(jobName) {
        description("SNAPSHOT: ${branchName}")
        parameters {
            stringParam('REPO', "https://github.com/ioneldumitru04-rgb/Snapshots", "SCM Repository")
            stringParam('BRANCH', branchName, 'SCM Branch to clone')
            booleanParam('RUN_DELIVERY', true, 'Run delivery')
        }
        
        // Trigger SCM polling
        
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url("https://github.com/ioneldumitru04-rgb/Snapshots")
                        }
                        branch(branchName)
                    }
                }
                scriptPath('Jenkinsfile')
            }
        }
        triggers {
            githubPush()
        }
    }
}