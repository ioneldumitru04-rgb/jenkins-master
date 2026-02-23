job('TOOL_Branchout') {
    description('Branchout from a target')
    label("docker-agent")

    parameters {
        stringParam('BRANCH', 'master', 'SCM branch to clone')
        stringParam('BRANCH_FROM', '', 'Branch from')
        stringParam('BRANCH_TO', '', 'Branch target, if doesnt exist create a new one')
        stringParam('BASE_REVISION', '', 'Revision to branchout, if empty, get head of the branch from')
    }


    steps {
        shell("""
            [[ -d scm ]] && rm -rdf scm
            git clone https://github.com/ioneldumitru04-rgb/scm

            ./scm/branchout_scripts/branchout.sh
        """)
    }

}