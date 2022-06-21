def call (Map config)
{
    node
        {
            stage('Nexus') 
                {  //sh "${config.dockerComposeLocation} -f ${config.dockerComposeElasticDestintination} build "
                    //sh "${config.dockerComposeLocation} -f ${config.dockerComposeNexusDestination} up -d"
                    //echo "Buid Image with docker-compose"
                    //echo "${config.dockerfileLocation}",
                    sh "dotnet pack ${config.DestinationProject}/DevOpsProject.csproj"
                    sh"dotnet nuget push"
                } 
        }
}