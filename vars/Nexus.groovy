def call (Map config)
{
    node
        {
            stage('Packing') 
                {  //sh "${config.dockerComposeLocation} -f ${config.dockerComposeElasticDestintination} build "
                    //sh "${config.dockerComposeLocation} -f ${config.dockerComposeNexusDestination} up -d"
                    //echo "Buid Image with docker-compose"
                    //echo "${config.dockerfileLocation}",
                    sh "dotnet pack ${config.ProjectName}"
                    sh "ls -a /var/jenkins_home/workspace/Back_main/source/DevOpsProject/bin/Debug/"
                    //sh"dotnet nuget push /srv/aoso/DevOps/backend/DevOpsProject/bin/Debug/aosora.1.0.0.nupkg --Source http://192.168.56.115:8081/repository/nuget-hosted/ -ApiKey 5f15c27d-1f8a-3baa-a136-cd624ba7c9b7 -source ./repository/Backend/"
                    //sh 'dotnet pack '
                }
            stage('Publish') 
                {  //sh "${config.dockerComposeLocation} -f ${config.dockerComposeElasticDestintination} build "
                    //sh "${config.dockerComposeLocation} -f ${config.dockerComposeNexusDestination} up -d"
                    //echo "Buid Image with docker-compose"
                    //echo "${config.dockerfileLocation}",
                    //sh "dotnet pack ${config.ProjectName}"
                   // nuget setapikey c479c4e5-89a4-3b19-b370-b488e09a4ecb -source http://192.168.56.115:8085/repository/{repository name}/
                    sh "dotnet nuget push /var/jenkins_home/workspace/Back_main/source/DevOpsProject/bin/Debug/aosora.1.0.0.nupkg -s http://192.168.56.115:8085/repository/nuget-hosted/ -k c479c4e5-89a4-3b19-b370-b488e09a4ecb "
                    //sh 'dotnet pack '
                    
                
                }
        }
}