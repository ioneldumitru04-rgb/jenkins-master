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
listView('TOOLS') {
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