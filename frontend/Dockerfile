## Use the official Node.js image as a base image
#FROM node:16
#
## Set the working directory inside the container
##WORKDIR /usr/src/app
#WORKDIR /src/components
#
## Copy package.json and package-lock.json to the working directory
#COPY package*.json ./
#
#RUN npm install yarn
#
#
## Install project dependencies
##RUN npm install
#RUN yarn install
#
## Copy the entire project to the working directory
#COPY . .
#
## Build the React app
##RUN npm run build
#RUN yarn build
#
## Expose the port the app runs on
#EXPOSE 3000
#
## Command to run the application
#CMD ["npm", "start"]
#
#
#
#
FROM node:18.12-alpine

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY package.json ./
COPY package-lock.json ./
RUN npm install --silent
RUN npm install react-scripts@3.4.1 -g --silent

# add app
COPY . ./

#EXPOSE 5173

# start app
RUN ["npm", "run", "build"]
CMD ["npm", "start"]