def call (Map config)
{
    node
        {

        dir("scripts/Ansible")
        {
            stage('Build Ansible ') 
                {    
                   
                   // sh "${config.dockerComposeLocation} -f ${config.elasticsearch} up -d"
                    //sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQube} up -d"
                    //sh "${config.dockerComposeLocation} -f ${config.dockerComposeNexus} up -d"
                    sh 'docker build -t ansible-app .'
                    sh 'docker run -dp 8888:8888 ansible-app'
                } 
        }}
}