job('my-simple-job') {
    description('Un job simplu creat cu DSL')
    label("agent")
    steps {
        shell('echo "Hello from DSL job!! After webhook and seed"')
    }

}