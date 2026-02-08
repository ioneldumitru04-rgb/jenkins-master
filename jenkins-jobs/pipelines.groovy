
// Funcție pentru a lua branch-urile remote
def getRemoteBranches(String gitUrl, String credentialsId) {
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

// Configurare
def gitUrl = 'https://github.com/ioneldumitru04-rgb/CRM-frontend'
def credentialsId = 'git-credentials'

// Ia toate branch-urile remote
def branches = getRemoteBranches(gitUrl, credentialsId)

println "Found branches: ${branches}"

// Creează job pentru fiecare branch
branches.each { branchName ->
    def jobName = "VER_${branchName.replaceAll('/', '-')}_FRONTEND"
    
    pipelineJob(jobName) {
        description("Build pentru branch: ${branchName}")
        
        parameters {
            stringParam('BRANCH', branchName, 'Branch to build')
            booleanParam('RUN_TESTS', true, 'Run tests')
        }
        
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(gitUrl)
                            credentials(credentialsId)
                        }
                        branches(branchName)
                    }
                }
                scriptPath('Jenkinsfile')
            }
        }
    }
}
branches.each { branchName ->
    def jobName = "PROD_${branchName.replaceAll('/', '-')}_BACKEND"
    
    pipelineJob(jobName) {
        description("Build pentru branch: ${branchName}")
        
        parameters {
            stringParam('BRANCH', branchName, 'Branch to build')
            booleanParam('RUN_TESTS', true, 'Run tests')
        }
        
        // Trigger SCM polling
        
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(gitUrl)
                            credentials(credentialsId)
                        }
                        branches(branchName)
                    }
                }
                scriptPath('Jenkinsfile')
            }
        }
    }
}