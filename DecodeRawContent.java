package xmashine.impactor;

import org.jetbrains.annotations.Contract;

class DecodeRawContent {

    /**
     * @Releases
     * VersionName,Version,VersionNumber,BuildNumber,securityPatches,Changelog
     *
     * @Beta
     * VersionName,Version,VersionNumber,BuildNumber,securityPatches,Changelog
     */

    private String VersionName = "";
    private String Version = "";
    private int VersionNumber = 0;
    private int BuildNumber = 0;
    private boolean SecurityPatches = false;
    private String ChangeLog = "";

    DecodeRawContent(String FileContent) {
        String[] RawContent = FileContent.split(",");
        RawContent[5] = RawContent[5].replace("@V", ",");
        RawContent[5] = RawContent[5].replace("@v", ",");

        this.VersionName = RawContent[0];
        this.Version = RawContent[1];
        this.VersionNumber = Integer.valueOf(RawContent[2]);
        this.BuildNumber = Integer.valueOf(RawContent[3]);
        this.SecurityPatches = Boolean.getBoolean(RawContent[4]);
        this.ChangeLog = RawContent[5];
    }

    DecodeRawContent(String VersionNameString, String VersionString, int VersionNumberString, int VersionCodeString, boolean implementsPatches, String ChangelogString) {
        this.VersionName = VersionNameString;
        this.Version = VersionString;
        this.VersionNumber = VersionNumberString;
        this.BuildNumber = VersionCodeString;
        this.SecurityPatches = implementsPatches;
        this.ChangeLog = String.valueOf(ChangelogString);
    }

    @Contract(pure = true)
    public final String toString()
    {
        return String.valueOf(VersionNumber) + String.valueOf(BuildNumber) + String.valueOf(SecurityPatches) + String.valueOf(ChangeLog);
    }

    @Contract(pure = true)
    final String getVersionName() {
        return VersionName;
    }

    @Contract(pure = true)
    public final String getVersion() {
        return Version;
    }

    @Contract(pure = true)
    final int getVersionNumber() {
        return VersionNumber;
    }

    @Contract(pure = true)
    final int getBuildNumber()
    {
        return BuildNumber;
    }

    @Contract(pure = true)
    final String getChangeLogIT() {
        return ChangeLog;
    }

    @Contract(pure = true)
    final boolean includeSecurityPatches() {
        return SecurityPatches;
    }

    @Contract(pure = true)
    final boolean isDistUpgrade(int CurrentVersion) {
        return VersionNumber - CurrentVersion >= 100;
    }
}
