FROM jenkins/jenkins:lts-jdk21

USER root

RUN apt-get update \
    && apt-get install -y lsb-release

RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc "https://download.docker.com/linux/debian/gpg"

RUN echo "deb [arch=$(dpkg --print-architecture) \
  signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
  https://download.docker.com/linux/debian \
  $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list

RUN apt-get update \
    && apt-get install -y docker-ce-cli

USER jenkins

RUN jenkins-plugin-cli --plugins \
    "analysis-model-api:latest" \
    "antisamy-markup-formatter:latest" \
    "apache-httpcomponents-client-4-api:latest" \
    "authentication-tokens:latest" \
    "blueocean-bitbucket-pipeline:latest" \
    "blueocean-commons:latest" \
    "blueocean-config:latest" \
    "blueocean-core-js:latest" \
    "blueocean-dashboard:latest" \
    "blueocean-display-url:latest" \
    "blueocean-events:latest" \
    "blueocean-git-pipeline:latest" \
    "blueocean-github-pipeline:latest" \
    "blueocean-i18n:latest" \
    "blueocean-jwt:latest" \
    "blueocean-personalization:latest" \
    "blueocean-pipeline-api-impl:latest" \
    "blueocean-pipeline-editor:latest" \
    "blueocean-pipeline-scm-api:latest" \
    "blueocean-rest-impl:latest" \
    "blueocean-rest:latest" \
    "blueocean-web:latest" \
    "blueocean:latest" \
    "bootstrap5-api:latest" \
    "bouncycastle-api:latest" \
    "branch-api:latest" \
    "build-timeout:latest" \
    "caffeine-api:latest" \
    "checks-api:latest" \
    "cloudbees-bitbucket-branch-source:latest" \
    "cloudbees-folder:latest" \
    "commons-lang3-api:latest" \
    "commons-text-api:latest" \
    "credentials-binding:latest" \
    "credentials:latest" \
    "data-tables-api:latest" \
    "display-url-api:latest" \
    "docker-commons:latest" \
    "docker-workflow:latest" \
    "durable-task:latest" \
    "echarts-api:latest" \
    "email-ext:latest" \
    "favorite:latest" \
    "font-awesome-api:latest" \
    "forensics-api:latest" \
    "git-client:latest" \
    "git:latest" \
    "github-api:latest" \
    "github-branch-source:latest" \
    "github-pullrequest:latest" \
    "github:latest" \
    "gradle:latest" \
    "gson-api:latest" \
    "handy-uri-templates-2-api:latest" \
    "htmlpublisher:latest" \
    "instance-identity:latest" \
    "ionicons-api:latest" \
    "jackson2-api:latest" \
    "jakarta-activation-api:latest" \
    "jakarta-mail-api:latest" \
    "javax-activation-api:latest" \
    "javax-mail-api:latest" \
    "jaxb:latest" \
    "jenkins-design-language:latest" \
    "jjwt-api:latest" \
    "joda-time-api:latest" \
    "jquery3-api:latest" \
    "json-api:latest" \
    "json-path-api:latest" \
    "junit:latest" \
    "ldap:latest" \
    "locale:latest" \
    "mailer:latest" \
    "matrix-auth:latest" \
    "matrix-project:latest" \
    "mina-sshd-api-common:latest" \
    "mina-sshd-api-core:latest" \
    "okhttp-api:latest" \
    "pam-auth:latest" \
    "pipeline-build-step:latest" \
    "pipeline-github-lib:latest" \
    "pipeline-graph-analysis:latest" \
    "pipeline-groovy-lib:latest" \
    "pipeline-input-step:latest" \
    "pipeline-milestone-step:latest" \
    "pipeline-model-api:latest" \
    "pipeline-model-definition:latest" \
    "pipeline-model-extensions:latest" \
    "pipeline-rest-api:latest" \
    "pipeline-stage-step:latest" \
    "pipeline-stage-tags-metadata:latest" \
    "pipeline-stage-view:latest" \
    "plain-credentials:latest" \
    "plugin-util-api:latest" \
    "prism-api:latest" \
    "pubsub-light:latest" \
    "resource-disposer:latest" \
    "scm-api:latest" \
    "script-security:latest" \
    "snakeyaml-api:latest" \
    "sse-gateway:latest" \
    "ssh-credentials:latest" \
    "ssh-slaves:latest" \
    "structs:latest" \
    "timestamper:latest" \
    "token-macro:latest" \
    "trilead-api:latest" \
    "variant:latest" \
    "warnings-ng:latest" \
    "workflow-aggregator:latest" \
    "workflow-api:latest" \
    "workflow-basic-steps:latest" \
    "workflow-cps:latest" \
    "workflow-durable-task-step:latest" \
    "workflow-job:latest" \
    "workflow-multibranch:latest" \
    "workflow-scm-step:latest" \
    "workflow-step-api:latest" \
    "workflow-support:latest" \
    "ws-cleanup:latest"