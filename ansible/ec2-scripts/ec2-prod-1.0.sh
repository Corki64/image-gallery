#!/usr/bin/bash

# Install packages
yum -y update
amazon-linux-extras install -y java-openjdk11
amazon-linux-extras install -y nginx1
yum install -y java-11-openjdk-devel git
su ec2-user -l -c 'curl -s "https://get.sdkman.io" | bash && source .bashrc && sdk install gradle 6.5'

# Configure/install custom software
cd /home/ec2-user
git clone https://github.com/Corki64/image-gallery.git
chown -R ec2-user:ec2-user image-gallery

CONFIG_BUCKET="s3://edu.au.cc.image-gallery-config.lac0084"
aws s3 cp ${CONFIG_BUCKET}/nginx/nginx.conf /etc/nginx/nginx.conf
aws s3 cp ${CONFIG_BUCKET}/nginx/default.d/image_gallery.conf /etc/nginx/default.d/image_gallery.conf

# Start/enable services
systemctl stop postfix
systemctl disable postfix
systemctl start nginx
systemctl enable nginx

su ec2-user -l -c 'cd ~/image-gallery && ./start' >/var/log/image_gallery.log 2>&1 &