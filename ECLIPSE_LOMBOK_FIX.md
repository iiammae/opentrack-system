# Fix Eclipse Lombok Issues

## Problem
Eclipse shows compilation errors like:
- "The method getToken() is undefined for the type LoginResponse"
- "The method setId(Long) is undefined for the type Bus"
- "The method getUsername() is undefined for the type LoginRequest"

Even though all entities have `@Data` and `@Builder` annotations.

## Root Cause
Eclipse's annotation processor isn't processing Lombok annotations properly.

## Solution 1: Clean Build (Fastest)

1. **Project** → **Clean...**
2. Select your project "opentrack-system"
3. Click **Clean** button
4. Wait for build to complete
5. **Project** → **Build Project** (or Ctrl+B)

If still not working, try Solution 2.

## Solution 2: Configure Lombok in Eclipse

1. **Window** → **Preferences** (or Eclipse → Preferences on macOS)
2. Navigate to: **Java** → **Compiler** → **Annotation Processing**
3. Check: ✓ **Enable project specific settings**
4. Check: ✓ **Enable annotation processing**
5. Check: ✓ **Enable processing in editor**
6. Click **Apply** → **OK**

## Solution 3: Reinstall Lombok

1. **Help** → **About Eclipse IDE**
2. Look for "Lombok" in the list
3. If not installed, or outdated:
   - **Help** → **Eclipse Marketplace**
   - Search: "lombok"
   - Install "Lombok" by projectlombok
   - Restart Eclipse

## Solution 4: Manual Rebuild (Nuclear Option)

1. Right-click project → **Delete** (keep files on disk)
2. **File** → **Import** → **General** → **Existing Projects into Workspace**
3. Select your project folder
4. Click **Finish**
5. Wait for automatic build

## Solution 5: Run with Maven (Most Reliable)

Open terminal in your project directory:

```bash
mvn clean install
mvn eclipse:clean eclipse:eclipse
```

Then refresh Eclipse: **F5** or Right-click project → **Refresh**

## Verification

Errors should disappear after a few seconds. If not:
1. Check **Problems** tab (usually bottom panel)
2. Right-click error → **Delete** if it persists after clean build
3. Restart Eclipse if errors still show

## FAQ

**Q: Still seeing red X marks?**
A: Do Solution 4 (nuclear rebuild). This always works.

**Q: Build works but IDE shows errors?**
A: Do Solution 2 (enable annotation processing).

**Q: pom.xml has Lombok?**
A: Check Maven dependency for Lombok in pom.xml:
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version>
    <scope>provided</scope>
</dependency>
```

Should be present with `scope: provided`.
```

```properties file="" isHidden
