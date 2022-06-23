import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import com.jcraft.jsch.UserInfo
import com.jcraft.jsch.Channel
import com.jcraft.jsch.ChannelExec
import java.util.Properties



def sshHost = '119.168.56.115'
def sshUser = 'hediane'
def sshPass = '123456'
def sshPort = 22

def localHost = '119.168.56.115'

def targetHost = '87.106.205.95'
def targetUser = 'root'
def targetPass = ''
def targetPort = 22
def call (Map config)
{
    node
        {
            stage('Opening connection ') 
                {   println "Opening connection to ${sshUser}@${sshHost}:${sshPort}"
                    Properties config = new Properties()
                    config.put("StrictHostKeyChecking", "no")
                    JSch jsch = new JSch()
                } 
            stage('connected') 
                {  Session sshSession = jsch.getSession(sshUser, sshHost, sshPort)
                    sshSession.setPassword(sshPass)
                    sshSession.setConfig(config)
                    sshSession.connect()
                    println "Connected"
                    println "Forwarding connection to ${targetHost}:${targetPort}"
                    def assignedPort = sshSession.setPortForwardingL(0, targetHost, targetPort)
                    println "Got port $assignedPort"
                } 
            stage('connected 2') 
                {   def result = ''
                    def ant = new AntBuilder()
                    // This bit is to stop ant hijacking stdout.
                    logger = ant.project.buildListeners.find { it instanceof org.apache.tools.ant.DefaultLogger }
                    logger.messageOutputLevel = 0
                    logger.emacsMode = true
                    // Ok. Do it...
                    ant.sshexec(
                    host: localHost,
                    port: assignedPort,
                    trust: true,
                    username: targetUser,
                    password: targetPass,
                    command: '/bin/hostname',
                    outputproperty: 'result',
                    verbose: false
                    )
                // And show the result
                println ant.project.properties.'result'
                } 
        }
}






