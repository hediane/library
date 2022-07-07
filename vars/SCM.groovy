def call (Map config)

{
    node
        {

            stage('GitSCM') {
            checkout([$class: 'GitSCM',
            branches: [[name: 'refs/heads/main']],
            userRemoteConfigs: [[
                url:"${config.scmurl}"]]

            ])

            }
        }
}