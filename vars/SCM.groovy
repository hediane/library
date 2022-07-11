def call (Map config)

{
    pipeline
        {
            agent any
        stages{
            stage('GitSCM') {
                steps{
                checkout([$class: 'GitSCM',
                branches: [[name: 'refs/heads/main']],
                userRemoteConfigs: [[
                    url:"${config.scmurl}"]]

            ])
                }
            }
        }
        }
}