-- テーブル削除
DROP TABLE IF EXISTS trips;
DROP TABLE IF EXISTS stop_times;
DROP TABLE IF EXISTS stops;

-- tripsテーブル作成
CREATE TABLE IF NOT EXISTS trips (
    route_id VARCHAR(16) NOT NULL,
    service_id VARCHAR(16) NOT NULL,
    trip_id VARCHAR(16) NOT NULL,
    trip_headsign VARCHAR(16) NOT NULL,
    direction_id INT,
    block_id VARCHAR(16),
    shape_id VARCHAR(16)
);

-- stop_timesテーブル作成
CREATE TABLE IF NOT EXISTS stop_times (
    trip_id VARCHAR(16) NOT NULL,
    arrival_time TIME NOT NULL,
    departure_time TIME NOT NULL,
    stop_id VARCHAR(32) NOT NULL,
    stop_sequence INT NOT NULL,
    stop_headsign VARCHAR(32),
    pickup_type SMALLINT NOT NULL,
    drop_off_type SMALLINT NOT NULL
);

-- stopsテーブル作成
CREATE TABLE IF NOT EXISTS stops (
    stop_id VARCHAR(32) NOT NULL PRIMARY KEY,
    stop_name VARCHAR(100) NOT NULL,
    platform_code VARCHAR(10),
    stop_lat NUMERIC(10, 8) NOT NULL,
    stop_lon NUMERIC(11, 8) NOT NULL,
    zone_id VARCHAR(32),
    location_type SMALLINT NOT NULL,
    parent_station VARCHAR(32)
);
\copy trips from '/docker-entrypoint-initdb.d/trips.csv' with csv header
\copy stop_times from '/docker-entrypoint-initdb.d/stop_times.csv' with csv header
\copy stops from '/docker-entrypoint-initdb.d/stops.csv' with csv header