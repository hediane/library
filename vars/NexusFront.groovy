def call (Map config)
{
    node
        {
            //dir("/var/jenkins_home/workspace/Front_main/source/DevOpsFront") {
                        stage('Packing') 

                        {
                            
                            sh "cd ${config.dist}"
                            sh 'npm pack'
                             sh 'ls -a'
                            sh "ls -a ${config.dist}"

                        }
            //}
            /*stage('Publish') 
                { 
                 sh "dotnet nuget push /var/jenkins_home/workspace/Back_main/source/DevOpsProject/bin/Debug/aosora.1.0.0.nupkg -s http://87.106.205.95:4002/repository/nuget-hosted/ -k 5f15c27d-1f8a-3baa-a136-cd624ba7c9b7 "
                //sh 'dotnet nuget push **\\nupkgs\\*.nupkg -s http://192.168.56.115:8081/repository/nuget-hosted/ -k 5f15c27d-1f8a-3baa-a136-cd624ba7c9b7 '
                    
                
                }*/
        }
        
}