def call (Map config)

{  
    node
        {   
           
            stage("GitSCM") {
            checkout([$class: 'GitSCM', 
            branches: [[name: 'refs/heads/main']], 
            userRemoteConfigs: [[
                //refspec: '+refs/tags/*:refs/remotes/origin/tags/*',
                url:"${config.scmurl}"]]
               
            ])
            sh "ls -a"
        }
        }
    

        
}