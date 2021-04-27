# Running Jenkins Locally

Configures the shared libraries that can then be used in scripted pipelines

- `docker-compose up`, wait until admin password provided (ready to run)
- Copy Admin password, go to browser, enter `localhost:8080`, enter admin password and submit
- Setup credentials for git (user/passphrase)
- Manage Jenkins > Configure System > Global Pipeline Configuration
  - Specify name that will be used for `@Library('')` (i.e. `jenkins-shared-library`)
  - Specify branch/tag that will be used (i.e. `main` as default in GitHub)
  - Select modern SCM and include the URL of the git project that includes the `jenkins-shared-library` code
  - Select correct credentials to access 
  - Save

## Example usage in scripted pipeline

```Jenkinsfile
@Library('jenkins-shared-library')_

node {
    stage('Example') {
  
        // Calls from jenkins-shared-library arguments can be provided
        helloWorld "Test"
    }
}
```

# Package Structure

The following represents the package structure:

- vars - The commands picked up by filename (i.e. `helloWorld.groovy` will translate to `helloWorld` in pipeline)
- src - The supporting source code used from within the call in the `groovy` var files
- resources - Contains any static resources/data required by the pipelines

## VAR example

The following snippet is an example `call` implementation for a Jenkins pipeline command

```groovy
// helloWorld.groovy
#!/usr/bin/env groovy
def call(String name = 'human') {
    echo "Hello, ${name}."
}
```

Every file within the `var` package that should be used within the pipeline should follow this structure. 


