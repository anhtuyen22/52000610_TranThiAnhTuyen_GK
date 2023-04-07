import { Link } from 'react-router-dom';
import classNames from 'classnames/bind';

import styles from './SearchItem.module.scss';
import Image from '~/components/Image';

const cx = classNames.bind(styles);

function SearchItem({ data, onClick }) {
  return (
    <Link to={`/detail/${data.id}`} className={cx('wrapper')} onClick={onClick}>
      <Image
        className={cx('product_img')}
        src={data.image ? require(`../../assets/images/${data.image}`) : ''}
        alt="Burger"
      />
      <div className={cx('info')}>
        <h4 className={cx('name')}>
          <span>{data.title}</span>
          {/* <FontAwesomeIcon className={cx('check')} icon={faCheckCircle} /> */}
        </h4>
        <span className={cx('price')}>${data.price}</span>
      </div>
    </Link>
  );
}

export default SearchItem;
