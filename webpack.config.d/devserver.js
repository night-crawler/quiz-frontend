config.devServer = config.devServer || {};
config.devServer.port = 3001;
config.devServer.watchOptions = {
    "aggregateTimeout": 3000,
    "poll": 1000
};
config.devServer.open = false;
