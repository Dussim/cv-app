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
    "analysis-model-api:11.15.0" \
    "antisamy-markup-formatter:162.v0e6ec0fcfcf6" \
    "apache-httpcomponents-client-4-api:4.5.14-208.v438351942757" \
    "authentication-tokens:1.119.v50285141b_7e1" \
    "blueocean-bitbucket-pipeline:1.27.11" \
    "blueocean-commons:1.27.11" \
    "blueocean-config:1.27.11" \
    "blueocean-core-js:1.27.11" \
    "blueocean-dashboard:1.27.11" \
    "blueocean-display-url:2.4.3" \
    "blueocean-events:1.27.11" \
    "blueocean-git-pipeline:1.27.11" \
    "blueocean-github-pipeline:1.27.11" \
    "blueocean-i18n:1.27.11" \
    "blueocean-jwt:1.27.11" \
    "blueocean-personalization:1.27.11" \
    "blueocean-pipeline-api-impl:1.27.11" \
    "blueocean-pipeline-editor:1.27.11" \
    "blueocean-pipeline-scm-api:1.27.11" \
    "blueocean-rest-impl:1.27.11" \
    "blueocean-rest:1.27.11" \
    "blueocean-web:1.27.11" \
    "blueocean:1.27.11" \
    "bootstrap5-api:5.3.2-3" \
    "bouncycastle-api:2.30.1.78.1-248.ve27176eb_46cb_" \
    "branch-api:2.1152.v6f101e97dd77" \
    "build-timeout:1.32" \
    "caffeine-api:3.1.8-133.v17b_1ff2e0599" \
    "checks-api:2.0.2" \
    "cloudbees-bitbucket-branch-source:888.v8e6d479a_1730" \
    "cloudbees-folder:6.858.v898218f3609d" \
    "commons-lang3-api:3.14.0-76.vda_5591261cfe" \
    "commons-text-api:1.11.0-108.v2c41b_8b_2b_269" \
    "credentials-binding:681.vf91669a_32e45" \
    "credentials:1319.v7eb_51b_3a_c97b_" \
    "data-tables-api:1.13.8-3" \
    "display-url-api:2.204.vf6fddd8a_8b_e9" \
    "docker-commons:439.va_3cb_0a_6a_fb_29" \
    "docker-workflow:580.vc0c340686b_54" \
    "durable-task:555.v6802fe0f0b_82" \
    "echarts-api:5.4.3-3" \
    "email-ext:2.105" \
    "favorite:2.218.vd60382506538" \
    "font-awesome-api:6.5.1-2" \
    "forensics-api:2.3.0" \
    "git-client:4.7.0" \
    "git:5.2.1" \
    "github-api:1.318-461.v7a_c09c9fa_d63" \
    "github-branch-source:1789.v5b_0c0cea_18c3" \
    "github-pullrequest:0.7.0" \
    "github:1.39.0" \
    "gradle:2.12" \
    "gson-api:2.10.1-15.v0d99f670e0a_7" \
    "handy-uri-templates-2-api:2.1.8-30.v7e777411b_148" \
    "htmlpublisher:1.36" \
    "instance-identity:185.v303dc7c645f9" \
    "ionicons-api:74.v93d5eb_813d5f" \
    "jackson2-api:2.17.0-379.v02de8ec9f64c" \
    "jakarta-activation-api:2.1.3-1" \
    "jakarta-mail-api:2.1.3-1" \
    "javax-activation-api:1.2.0-7" \
    "javax-mail-api:1.6.2-10" \
    "jaxb:2.3.9-1" \
    "jenkins-design-language:1.27.11" \
    "jjwt-api:0.11.5-112.ve82dfb_224b_a_d" \
    "joda-time-api:2.12.7-29.v5a_b_e3a_82269a_" \
    "jquery3-api:3.7.1-1" \
    "json-api:20240303-41.v94e11e6de726" \
    "json-path-api:2.9.0-33.v2527142f2e1d" \
    "junit:1265.v65b_14fa_f12f0" \
    "ldap:725.v3cb_b_711b_1a_ef" \
    "locale:314.v22ce953dfe9e" \
    "mailer:470.vc91f60c5d8e2" \
    "matrix-auth:3.2.2" \
    "matrix-project:822.824.v14451b_c0fd42" \
    "mina-sshd-api-common:2.12.1-101.v85b_e08b_780dd" \
    "mina-sshd-api-core:2.12.1-101.v85b_e08b_780dd" \
    "okhttp-api:4.11.0-172.vda_da_1feeb_c6e" \
    "pam-auth:1.11" \
    "pipeline-build-step:540.vb_e8849e1a_b_d8" \
    "pipeline-github-lib:61.v629f2cc41d83" \
    "pipeline-graph-analysis:216.vfd8b_ece330ca_" \
    "pipeline-groovy-lib:727.ve832a_9244dfa_" \
    "pipeline-input-step:477.v339683a_8d55e" \
    "pipeline-milestone-step:119.vdfdc43fc3b_9a_" \
    "pipeline-model-api:2.2205.vc9522a_9d5711" \
    "pipeline-model-definition:2.2205.vc9522a_9d5711" \
    "pipeline-model-extensions:2.2205.vc9522a_9d5711" \
    "pipeline-rest-api:2.34" \
    "pipeline-stage-step:312.v8cd10304c27a_" \
    "pipeline-stage-tags-metadata:2.2205.vc9522a_9d5711" \
    "pipeline-stage-view:2.34" \
    "plain-credentials:182.v468b_97b_9dcb_8" \
    "plugin-util-api:3.8.0" \
    "prism-api:1.29.0-11" \
    "pubsub-light:1.18" \
    "resource-disposer:0.23" \
    "scm-api:683.vb_16722fb_b_80b_" \
    "script-security:1341.va_2819b_414686" \
    "snakeyaml-api:2.2-111.vc6598e30cc65" \
    "sse-gateway:1.27" \
    "ssh-credentials:337.v395d2403ccd4" \
    "ssh-slaves:2.948.vb_8050d697fec" \
    "structs:338.v848422169819" \
    "timestamper:1.27" \
    "token-macro:400.v35420b_922dcb_" \
    "trilead-api:2.142.v748523a_76693" \
    "variant:60.v7290fc0eb_b_cd" \
    "warnings-ng:10.7.0" \
    "workflow-aggregator:600.vb_57cdd26fdd7" \
    "workflow-api:1291.v51fd2a_625da_7" \
    "workflow-basic-steps:1058.vcb_fc1e3a_21a_9" \
    "workflow-cps:3908.vd6b_b_5a_a_54010" \
    "workflow-durable-task-step:1331.vc8c2fed35334" \
    "workflow-job:1385.vb_58b_86ea_fff1" \
    "workflow-multibranch:773.vc4fe1378f1d5" \
    "workflow-scm-step:427.v4ca_6512e7df1" \
    "workflow-step-api:678.v3ee58b_469476" \
    "workflow-support:920.v59f71ce16f04" \
    "ws-cleanup:0.46"