#!groovy
/********************************************************************************
***** Description :: This Custom Library is used to Deploy to Remote Server *****
***** Author      :: Mukul Garg                                             *****
***** Date        :: 04/24/2017                                             *****
***** Revision    :: 2.0                                                    *****
********************************************************************************/

import com.sapient.devops.deploy.remote

def call(body) {
  def config = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()

  try {
      def remoteDeploy = new remote()
      echo "config remote IP : "+"${config.REMOTE_IP}"
      echo "config REMOTE USER : "+"${config.REMOTE_USER}"
      remoteDeploy.setValue("root", "10.202.11.199", "/app/appx_html/help/", "/app/deployables/setup.sh")
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
