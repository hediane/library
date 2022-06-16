def call (Map config)
{
    node
    {
         
        /*stage('SONAR BUILD') 
                {   sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQube} build"
                    sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQube} up -d"
                }*/
        
        stage('Quality Gates')
            {
               def qualitygate = waitForQualityGate()
               sleep(10)

              if (qualitygate.status != "OK") 
                {
                    waitForQualityGate abortPipeline: true     
                }
            }
    }
}