def call (Map config)
{
    node
    {
         
        stage('SONAR QUALITY ANALYSIS')
               {    
                  
                    def scannerHome = tool name:'SonarScanner for MSBuild'
                    withSonarQubeEnv('SonarQube')
                    {
                        dir("${config.ProjectPack1}") {
                        sh " ls -la ${pwd()}"
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll begin /k:Back /d:sonar.login=sqa_bb42b9b3abb679965154c5e4d9f3ebb031405e54"
                        //580029abd6076347f7f7115089508a365415fef3 "
                        sh "dotnet build"
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll end /d:sonar.login=sqa_bb42b9b3abb679965154c5e4d9f3ebb031405e54"
                        //a650a854dfc5fdfd835f432b6cbf52f369f6a2b1"
                        }
                    }
               }
                stage("QUALITY GATE")
                        {
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
                                
                        }
                            
                            
        }
                        
            

 
    
}