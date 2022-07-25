def call (Map config)
{
    node
    {
       stage('Analysis quality') 
            {   def scannerHome = tool name: "${config.SonarQubeTool}";
                    withSonarQubeEnv("${config.SonarQubeEnv}") {
                            dir("${config.source}") {
                                    sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${config.ProjectName} -Dsonar.login=${config.SonarQubeToken}"
                                    
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