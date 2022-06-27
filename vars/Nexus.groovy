def call (Map config)
{
    node
        {
        dir("/var/lib/jenkins/workspace/Back_main/source/DevOpsProject")
             { /*  stage('Packing') 
                {  
                   
                //sh "${config.dockerComposeLocation} -f ${config.dockerComposeNexusDestination} up -d"
                   sh "dotnet clean"
                   sh "dotnet build"
                   sh "dotnet pack"
                   //sh "ls -a /var/lib/jenkins/workspace/Back_main/source/DevOpsProject/bin/Debug/"
                   //sh "ls -a /var/lib/jenkins/workspace/Back_main/source/DevOpsProject/bin/Debug/aosora.1.0.0.nupkg"
                    //sh 'dotnet clean'
                    //sh 'dotnet build --configuration Release'
                    //sh 'dotnet pack --no-build --output nupkgs'
                }*/
            stage('Publish') 
                { 
                 sh "dotnet push /var/lib/jenkins/workspace/Back_main/source/DevOpsProject/DevOpsProject.csproj -s http://192.168.56.115:8081/repository/nuget-hosted/ -k 5f15c27d-1f8a-3baa-a136-cd624ba7c9b7 "
                //sh 'dotnet nuget push **\\nupkgs\\*.nupkg -s http://192.168.56.115:8081/repository/nuget-hosted/ -k 5f15c27d-1f8a-3baa-a136-cd624ba7c9b7 '
                    
                
         
                    }       }
        }
        
}