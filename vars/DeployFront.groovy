def call (Map config) {
    node
        {
            stage('DEPLOY DEV SERVEUR ')
                {
                    wrap([$class: 'BuildUser']) {
                    def user = env.BUILD_USER_ID
                if ("${config.devValidator}".contains("${user}")) {
                    if (input (id: 'someId', message: 'Do you want to approve the deploy in production?',
                            parameters: [choice( choices: ['No', 'Yes'], description: 'DO YOU CONFIRME', name: 'some name')]) == 'Yes') {
                        sh "ansible -m ping  front"
                        sh "ansible-playbook ${config.ansibleplaybook} -i ${config.ansibleinventory}"
                        //sh "ssh ${config.DevServer} 'docker pull ${config.ImagePull}'"
                        //sh "ssh ${config.DevServer} 'mkdir -p ${config.DestinationFolder}'"
                        //sh "scp  ${config.dockerComposeDestination} ${config.DevServer}:${config.DestinationFolder}"
                        //sh "ssh ${config.DevServer} docker-compose -f ${config.dockerComposeDestination} up -d"

                        }else{
                        input message: 'ABORD'
                        }
                }
                    else
                     {
                    input message: "SORRY YOU DON'T HAVE ACCESS"
                     }
                    }
                }
        }
}
