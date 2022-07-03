def call (Map config)

{  
    node
        {   
            stage("ssh_cnx") {
                    
                    sh 'ssh root@87.106.205.95 docker ps  '
                    sh 'ssh root@87.106.205.95 docker pull 192.168.56.115:18443/ubuntu '
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