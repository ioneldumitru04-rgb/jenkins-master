listView('Production') {
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
listView('Verification') {
    description('Verification')
    jobs {
        regex('VER_.*')
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