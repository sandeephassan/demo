# led-shared (sample Jenkins Shared Library)

This repository contains a minimal Jenkins Shared Library that installs build tooling used by the `led-firmware` pipeline.

Files:

- `vars/prepareEnvironment.groovy` — callable step `prepareEnvironment(projectDir)` that installs packages via `apt-get`.
- `scripts/prepare.sh` — optional shell script run by the library.

Install in Jenkins:

1. Manage Jenkins → Configure System → Global Pipeline Libraries → Add.
2. Name: `led-shared`
3. Default version: `main`
4. Retrieval method: Modern SCM → Git → Repository URL: `https://github.com/sandeepds077/shared_library`
5. Save.

Usage in `Jenkinsfile`:

```groovy
library 'led-shared@main'
prepareEnvironment(env.PROJECT_DIR)
```
