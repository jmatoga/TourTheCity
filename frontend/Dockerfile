# Use the official Node.js image as a base image
FROM node:16

# Set the working directory inside the container
#WORKDIR /usr/src/app
WORKDIR /src/components

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

RUN npm install yarn


# Install project dependencies
#RUN npm install
RUN yarn install

# Copy the entire project to the working directory
COPY . .

# Build the React app
#RUN npm run build
RUN yarn build

# Expose the port the app runs on
EXPOSE 3000

# Command to run the application
CMD ["npm", "start"]




