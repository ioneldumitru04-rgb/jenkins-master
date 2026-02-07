listView('PRODUCTION') {
    description('PRODUCTION VIEW')
    jobs {
        regex('PROD_.*')
    }
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}