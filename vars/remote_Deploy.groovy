#!groovy
import com.yantrashala.devops.deploy.remote

def call(body) {
  def config = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()

  try {
      def remoteDeploy = new remote()
      remoteDeploy.setValue("${config.REMOTE_USER}", "${config.REMOTE_IP}", "${config.DEPLOY_PATH}", "${config.SCRIPT}")
      remoteDeploy.deploy()
  }
  catch (Exception error)
  {
      wrap([$class: 'AnsiColorBuildWrapper']) {
         echo "\u001B[41m[ERROR] ${error}"
         throw error
      }
  }
}
