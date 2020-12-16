// Merges the provided configuration with the pipeline config

import com.griddynamics.devops.mpl.MPLManager

def call(cfg) {
  //println "Before Merge:${cfg.toString()}"
  if (cfg.returns != null) MPLManager.instance.configMerge(cfg.returns)
}