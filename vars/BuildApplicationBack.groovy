
def call (Map config) {
    node
        {
        stage ('BUILD PROJECT ')
            {
           
            dir("${config.DestinationProject}")
               {
                sh'dotnet restore'
                sh'dotnet build'
               }
            }
        }
}
