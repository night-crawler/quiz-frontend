FROM node:11-alpine

ENV NODE_ENV=production
EXPOSE 5000

RUN yarn global add serve

WORKDIR /application

COPY *.js /application/build/
COPY *.html /application/build/

RUN chown -hR node: /application

USER node

CMD ["serve", "-s", "-l", "tcp://0.0.0.0:5000", "build"]
