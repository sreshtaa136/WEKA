import React from 'react';
import './help-style.css';

// Help page component
const Help = () => {
    return(
        <div className="help">
            <div className="helpContent">
                <h1>Weka 3: Machine Learning Software in Java</h1>
                <p>
                    Weka is a collection of machine learning algorithms for data mining
                    tasks. It contains tools for data preparation, classification, regression, clustering, association
                    rules mining, and visualization.
                </p>
                <p>
                    Found only on the islands of New Zealand, the Weka is a
                    flightless bird with an inquisitive nature. The name is
                    pronounced like "way·kuh"
                    and the bird sounds like
                    <a href="https://www.youtube.com/shorts/l3AcsjO29Ow"> this </a>.
                </p>
                <p>
                    Weka is open source software issued under the
                    <a href="https://www.gnu.org/licenses/gpl.html" target="_blank"> GNU General Public License </a>.
                </p>
                <p>
                    We have put together several
                    <a href="https://www.cs.waikato.ac.nz/ml/weka/courses.html" target="_blank"> free online courses </a>
                    that teach machine learning and data mining using Weka. The videos for the courses are available
                    <a href="https://www.youtube.com/user/WekaMOOC" target="_blank"> on Youtube </a>.
                </p>
                <p>
                    Weka supports
                    <a href="https://deeplearning.cms.waikato.ac.nz"> deep learning </a> !
                </p>
                <p></p>
            </div>

            <div className="line"></div>

            <div className="content-three">
                Getting started
                <ul>
                    <li><a href="https://waikato.github.io/weka-wiki/requirements/">Requirements</a>
                    </li>
                    <li><a href="https://waikato.github.io/weka-wiki/downloading_weka/">Download</a>
                    </li>
                    <li><a href="https://waikato.github.io/weka-wiki/documentation/">Documentation</a>
                    </li>
                    <li><a href="https://waikato.github.io/weka-wiki/faq/">FAQ</a>
                    </li>
                    <li><a href="https://waikato.github.io/weka-wiki/getting_help/">Getting Help</a>
                    </li>
                </ul>
            </div>

            <div className="content-three">
                Further information
                <ul>
                    <li><a href="https://waikato.github.io/weka-wiki/citing_weka/">Citing Weka</a>
                    </li>
                    <li><a href="https://waikato.github.io/weka-wiki/datasets/">Datasets</a>
                    </li>
                    <li><a href="https://waikato.github.io/weka-wiki/academic/related_projects/">Related Projects</a>
                    </li>
                    <li><a href="https://www.cs.waikato.ac.nz/~ml/weka/miscellaneous.html">Miscellaneous Code</a>
                    </li>
                    <li><a href="https://waikato.github.io/weka-wiki/literature/">Other Literature</a>
                    </li>
                </ul>
            </div>

            <div className="content-three">
                Developers
                <ul>
                    <li><a href="https://waikato.github.io/weka-wiki/development/">Development</a>
                    </li>
                    <li><a href="https://waikato.github.io/weka-wiki/history/">History</a>
                    </li>
                    <li><a href="https://waikato.github.io/weka-wiki/development/#source-code-repository">Subversion</a>
                    </li>
                    <li><a href="https://waikato.github.io/weka-wiki/development/#code-credits">Contributors</a>
                    </li>
                    <li><a href="https://waikato.github.io/weka-wiki/faqs/commercial_applications/">Commercial
                        licenses</a>
                    </li>
                </ul>
            </div>
        </div>
    );
}

export default Help;