FROM bellsoft/liberica-openjdk-debian:23

CMD ["gradle"]

ENV GRADLE_HOME=/opt/gradle

RUN set -o errexit -o nounset \
    && echo "Adding gradle user and group" \
    && groupadd --system --gid 1000 gradle \
    && useradd --system --gid gradle --uid 1000 --shell /bin/bash --create-home gradle \
    && mkdir /home/gradle/.gradle \
    && chown --recursive gradle:gradle /home/gradle \
    \
    && echo "Symlinking root Gradle cache to gradle Gradle cache" \
    && ln --symbolic /home/gradle/.gradle /root/.gradle

VOLUME /home/gradle/.gradle

WORKDIR /home/gradle

RUN set -o errexit -o nounset \
    && apt-get update \
    && apt-get install --yes --no-install-recommends \
        unzip \
        wget \
        \
        openssh-client \
    && rm --recursive --force /var/lib/apt/lists/*

ENV GRADLE_VERSION="8.10.2"
ARG GRADLE_DOWNLOAD_SHA256=31c55713e40233a8303827ceb42ca48a47267a0ad4bab9177123121e71524c26
RUN set -o errexit -o nounset \
    && echo "Downloading Gradle" \
    && wget --no-verbose --output-document=gradle.zip "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" \
    \
    && echo "Checking Gradle download hash" \
    && echo "${GRADLE_DOWNLOAD_SHA256} *gradle.zip" | sha256sum --check - \
    \
    && echo "Installing Gradle" \
    && unzip gradle.zip \
    && rm gradle.zip \
    && mv "gradle-${GRADLE_VERSION}" "${GRADLE_HOME}/" \
    && ln --symbolic "${GRADLE_HOME}/bin/gradle" /usr/bin/gradle

USER gradle

RUN set -o errexit -o nounset \
    && echo "Testing Gradle installation" \
    && gradle --version

USER root

ENV ANDROID_HOME=/opt/android-sdk
ARG ANDROID_SDK_VERSION=11076708
ARG ANDROID_SDK_DOWNLOAD_SHA256=2d2d50857e4eb553af5a6dc3ad507a17adf43d115264b1afc116f95c92e5e258

RUN set -o errexit -o nounset \
    && echo "Downloading Android SDK" \
    && wget --no-verbose --output-document=android-sdk.zip "https://dl.google.com/android/repository/commandlinetools-linux-${ANDROID_SDK_VERSION}_latest.zip" \
    \
    && echo "Checking Android SDK download hash" \
    && echo "${ANDROID_SDK_DOWNLOAD_SHA256} *android-sdk.zip" | sha256sum --check - \
    \
    && echo "Installing Android SDK" \
    && unzip android-sdk.zip \
    && rm android-sdk.zip \
    && mkdir -p "${ANDROID_HOME}/cmdline-tools/tools" \
    && mv "cmdline-tools/"* "${ANDROID_HOME}/cmdline-tools/tools/"

ENV PATH=${PATH}:${ANDROID_HOME}/cmdline-tools/latest/bin:${ANDROID_HOME}/cmdline-tools/tools/bin:${ANDROID_HOME}/platform-tools

RUN set -o errexit -o nounset \
    && echo "Accepting Android SDK licenses" \
    && yes | sdkmanager --licenses

RUN set -o errexit -o nounset \
    && echo "Installing Android SDK build-tools" \
    && sdkmanager --install "build-tools;34.0.0"\
    && sdkmanager --install "platforms;android-34"\
    && sdkmanager --install "platform-tools"
