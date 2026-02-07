listview("PRODUCTION") {
    description("PRODUCTION VIEEW")
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