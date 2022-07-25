def call (Map config)
{
    node
    {
         
        stage('SONAR QUALITY ANALYSIS')
               {    
                  
                    def scannerHome = tool name:"${config.SonarQubeTool}"
                    withSonarQubeEnv("${config.SonarQubeEnv}")
                    {
                        dir("${config.source}") {
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll begin /k:${config.ProjectName} /d:sonar.login=${config.SonarQubeToken}"
                        sh "dotnet build "
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll end /d:sonar.login=${config.SonarQubeToken}"
                        
                        }
                    }
               }
                stage("QUALITY GATE")
                        {
                            def qualitygate = waitForQualityGate()

                                if (qualitygate.status != "OK")
                                        {

                                        waitForQualityGate abortPipeline: true
                                       
                                        } 
                                
                                
                        }
                            
                            
        }
                        
            

 
    
}