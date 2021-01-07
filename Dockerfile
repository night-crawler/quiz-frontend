FROM node:11-alpine

ENV NODE_ENV=production
EXPOSE 5000

RUN yarn global add serve

USER node

WORKDIR /application

COPY *.js /application/build/
COPY *.html /application/build/

CMD ["serve", "-s", "-l", "tcp://0.0.0.0:5000", "build"]
