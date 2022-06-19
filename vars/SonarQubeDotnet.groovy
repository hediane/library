def call (Map config)
{
    node
    {
         
       /* stage('SONAR BUILD') 
                {  sh"ls -a scripts/Back"
                     //sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQube} build"
                    sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQube} up -d"
                }*/
    
        stage('Sonar Quality Analysis')
               {    
                  
                    def scannerHome = tool name:'SonarScanner for MSBuild'
                    withSonarQubeEnv('SonarQube')
                    {
                        dir("${config.ProjectName}") {
                        sh " ls -la ${pwd()}"
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll begin /k:Back /d:sonar.login=eb77d83ff776ed7e511ccb6f98db5163f1af4f9f "
                        sh "dotnet build"
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll end /d:sonar.login=eb77d83ff776ed7e511ccb6f98db5163f1af4f9f"
                        //a650a854dfc5fdfd835f432b6cbf52f369f6a2b1"
                        }
                    }
                   
                   
                }
            stage("Quality gate")
            {
                def qualitygate = waitForQualityGate()

                    if (qualitygate.status != "OK")
                            {

                               echo "WAIT WAIT WAIT  !!"
                               waitForQualityGate abortPipeline: true     


                            } 
                    
            }
            

 
    }
}