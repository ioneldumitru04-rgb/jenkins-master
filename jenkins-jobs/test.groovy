job('my-simple-job') {
    description('Un job simplu creat cu DSL')
    
    steps {
        shell('echo "Hello from DSL job!!"')
    }

}