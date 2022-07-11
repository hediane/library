def call (Map config)
{
    pipeline
        {
           
        agent any
        stages
        {
         
        stage('SONAR QUALITY ANALYSIS')
               {    
                   environment { 
                    scannerHome = tool name:"${config.SonarQubeTool}"
                        }
                  steps{
                    //def  = tool name:"${config.SonarQubeTool}"
                     
                    withSonarQubeEnv("${config.SonarQubeEnv}")
                    {
                        dir("${config.source}") {
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll begin /k:${config.ProjectName} /d:sonar.login=${config.SonarQubeToken}"
                        sh "dotnet build "
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll end /d:sonar.login=${config.SonarQubeToken}"
                        
                        }
                    }
                  }
               }
                stage("QUALITY GATE")
                        { 
                        steps{
                            def qualitygate = waitForQualityGate()

                                if (qualitygate.status != "OK")
                                        {

                                        input message: "CHECK YOUR QUALITY GATE"
                                        waitForQualityGate abortPipeline: true
                                       
     
                                        } 
                                else 
                                        {
                                        input message: "YOUR APP IS READY TO PACKGING"
                                        }
                                
                        }}
                            
                            
        }
                        
            

 
    
}}