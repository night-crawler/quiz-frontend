config.devServer = config.devServer || {};
config.devServer.port = 3001;
config.devServer.watchOptions = {
    "aggregateTimeout": 5000,
    "poll": 1000
};
