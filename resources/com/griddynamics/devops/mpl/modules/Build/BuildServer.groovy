CFG.'configuration_property' = CFG.'configuration_property' ?: 'release'
if(CFG.'web_build_type' != null)
{
    MPLModule("Build Server${CFG.'web_build_type'}", CFG)
}
