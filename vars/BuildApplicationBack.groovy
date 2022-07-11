
def call (Map config) {
    node
        {
        stage ('BUILD PROJECT ')
            {
           
            dir("${config.source}")
               {
                sh'dotnet restore'
                sh'dotnet build'
               }
            }
        }
}
