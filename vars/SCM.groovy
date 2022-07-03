def call (Map config)

{  
    node
        {   
            stage("ssh_cnx") {
                    
                    sh 'ssh root@87.106.205.95 docker ps  '
                }
            stage("GitSCM") {
            checkout([$class: 'GitSCM', 
            branches: [[name: 'refs/heads/main']], 
            userRemoteConfigs: [[
                //refspec: '+refs/tags/*:refs/remotes/origin/tags/*',
                url:"${config.scmurl}"]]
               
            ])
        }
        }
    

        
}