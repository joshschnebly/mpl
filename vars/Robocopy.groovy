def robocopy(cmd)
{
	// robocopy uses non-zero exit code even on success, status greater than 3 is fine
	def status = bat returnStatus: true, script: "ROBOCOPY ${cmd} /nfl /ndl /np"
	echo "ROBOCOPY returned ${status}"
	if (status < 0 || status > 3)
	{
		error("ROBOCOPY failed")
	}
}