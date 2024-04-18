FROM eclipse-temurin:21-jdk-jammy

CMD ["gradle"]

ENV GRADLE_HOME /opt/gradle

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

ENV GRADLE_VERSION 8.7
ARG GRADLE_DOWNLOAD_SHA256=194717442575a6f96e1c1befa2c30e9a4fc90f701d7aee33eb879b79e7ff05c0
RUN set -o errexit -o nounset \
    && echo "Downloading Gradle" \
    && wget --no-verbose --output-document=gradle.zip "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-all.zip" \
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

ENV ANDROID_HOME /opt/android-sdk
ARG ANDROID_SDK_VERSION=10406996
ARG ANDROID_SDK_DOWNLOAD_SHA256=8919e8752979db73d8321e9babe2caedcc393750817c1a5f56c128ec442fb540

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

ENV PATH ${PATH}:${ANDROID_HOME}/cmdline-tools/latest/bin:${ANDROID_HOME}/cmdline-tools/tools/bin:${ANDROID_HOME}/platform-tools

RUN set -o errexit -o nounset \
    && echo "Accepting Android SDK licenses" \
    && yes | sdkmanager --licenses

RUN set -o errexit -o nounset \
    && echo "Installing Android SDK build-tools" \
    && sdkmanager --install "build-tools;34.0.0"\
    && sdkmanager --install "platforms;android-34"\
    && sdkmanager --install "platform-tools"
