listView('DELIVERY') {
    description('PRODUCTION VIEW')
    jobs {
        regex('DELIVERY.*')
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
        regex('VERIFICATION_.*')
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
listView('Snapshot') {
    description('Snapshot validation')
    jobs {
        regex('SNAPSHOT_.*')
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
listView('Tools') {
    description('TOOLS USED IN CI')
    jobs {
        regex('TOOL_.*')
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