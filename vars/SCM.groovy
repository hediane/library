def call (Map config)

{  
    node
        {   
           
            stage("GitSCM") {
            checkout([$class: 'GitSCM', 
            branches: [[name: 'refs/heads/main']], 
            userRemoteConfigs: [[
                //refspec: '+refs/tags/*:refs/remotes/origin/tags/*',
                url:"${config.scmurl}"]]
               
            ])
                sh "ls -a"
                sh "ls -la ${pwd()}"
                 sh "ls -la ${pwd()}/source"
                 sh "mkdir -p /srv/aoso/DevOps/backend "
                 sh "ls -a /srv/aoso/DevOps/backend/"
                 sh "cp -r ${config.DestinationFolder} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationProject}"
                 sh "ls -la ${config.DestinationProject} "
                 sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFile}"
                 sh "ls -la ${config.DestinationNginx} "
                 sh "ls -la ${config.DestinationFile} "

        }
            stage ('BUILD PROJECT ')
            {
               dir("${config.DestinationProject}") 
               {
                sh"dotnet restore"
                sh"dotnet build"
                sh"dotnet publish"
               }
            }
        }
    

        
}