def call (Map config)
{
    node
        {
            stage('Elasticsearch & Kibana') 
                {  //sh "${config.dockerComposeLocation} -f ${config.dockerComposeElasticDestintination} build "
                    sh "${config.dockerComposeLocation} -f ${config.dockerComposeElasticDestintination} up -d"
                    echo "Buid Image with docker-compose"
                    //echo "${config.dockerfileLocation}",
                } 
        }
}