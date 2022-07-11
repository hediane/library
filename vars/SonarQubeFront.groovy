def call (Map config)
{
     pipeline
        {
        agent any
        stages
        {
       stage('Analysis quality') 
        steps{
            {   def scannerHome = tool name: "${config.SonarQubeTool}";
                    withSonarQubeEnv("${config.SonarQubeEnv}") {
                            dir("${config.source}") {
                                    sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${config.ProjectName} -Dsonar.login=${config.SonarQubeToken}"
                                    
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
                }
            }
    }
}}