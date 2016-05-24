ruleset {
  description 'Test experimental'

  ruleset('rulesets/basic.xml')
  ruleset('rulesets/braces.xml')
  ruleset('rulesets/concurrency.xml')
  ruleset('rulesets/convention.xml') {
    'NoDef' {
      excludeRegex = /"[^"]+"/
    }
  }
  ruleset('rulesets/design.xml')
  ruleset('rulesets/dry.xml')
  ruleset('rulesets/exceptions.xml')
  ruleset('rulesets/formatting.xml')
  ruleset('rulesets/generic.xml')
  ruleset('rulesets/groovyism.xml')
  ruleset('rulesets/imports.xml')
  ruleset('rulesets/jdbc.xml')
  ruleset('rulesets/junit.xml')
  ruleset('rulesets/logging.xml')
  ruleset('rulesets/naming.xml') {
    'MethodName' enabled: false
  }
  ruleset('rulesets/security.xml')
  ruleset('rulesets/size.xml')
  ruleset('rulesets/unnecessary.xml')
  ruleset('rulesets/unused.xml') {
    'UnusedPrivateField' {
        enabled = true
        ignoreFieldNames = 'lastUpdated, dateCreated, serialVersionUID'
    }
  }
}
